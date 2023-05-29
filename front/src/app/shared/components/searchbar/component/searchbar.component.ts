import { Component, OnInit } from '@angular/core';
import { LevenshteinService } from '../services/levenshtein.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { metaphone } from 'metaphone';
import { Prestation } from 'src/app/shared/model/prestation';
import { FormGroupDirective } from '@angular/forms';

@Component({
  
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.scss']
})
export class SearchbarComponent implements OnInit {
  private prestationsApi?: Prestation[];
  private tempList?: Prestation[];
  private distanceLevenshtein?: number;
  private distanceMetaphone?: number;
  
  public prestationsSearch?: Prestation[];
  public searchForm: FormGroup<any>;
  
  constructor(
    private levenshteinService: LevenshteinService,
    private prestationService: PrestationService,
    public fb: FormBuilder
  ) { 
    this.searchForm = this.fb.group({ search: [''] });
  }
  
  
  ngOnInit() {
    this.prestationService.getPrestations().subscribe((response) => {
      this.prestationsApi = response;
    });
  }

  onSubmit() {
    if (this.searchForm.value['search']) {
      this.getSearchList(this.searchForm.value['search']);
    } else {
      this.prestationsSearch = this.prestationsApi;
    }
  }

  getSearchList(search: string) {
    const DISTANCE_LEVENSHTEIN = 1;
    const DISTANCE_METAPHONE = 1;
    this.tempList = [];

    this.prestationsApi?.forEach((prestation) => {
      this.distanceLevenshtein = this.levenshteinService.calculate(
        search.toLowerCase(),
        prestation?.location?.city ? prestation.location.city.toLowerCase() : ''
      );

      this.distanceMetaphone = this.levenshteinService.calculate(
        metaphone(search),
        metaphone(prestation?.location?.city ? prestation.location.city.toLowerCase() : '')
      );

      if (
        this.distanceLevenshtein <= DISTANCE_LEVENSHTEIN ||
        this.distanceMetaphone <= DISTANCE_METAPHONE
      ) {
        this.tempList?.push(prestation);
      }

    });

    this.prestationsSearch = this.tempList;
    this.searchForm.reset();
  }
}
