import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LevenshteinService } from '../services/levenshtein.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { metaphone } from 'metaphone';
import { Prestation } from 'src/app/shared/model/prestation';
import { FormGroupDirective } from '@angular/forms';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.scss'],
})
export class SearchbarComponent implements OnInit {
  private prestationsApi?: Prestation[];
  private tempList?: Prestation[];
  private distanceLevenshtein?: number;
  private distanceMetaphone?: number;

  public prestationsSearch?: Prestation[];
  public searchForm: FormGroup<any>;

  @Output()
  prestationsEmitter: EventEmitter<Prestation[]> = new EventEmitter();

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
    if (this.searchForm.value['search'].trim()) {
      this.getPrestationsContainingWordLike(this.searchForm.value['search']);
    } else {
      this.prestationsSearch = this.prestationsApi;
    }
    this.sendPrestations();
  }

  getPrestationsContainingWordLike(search: string) {
    search = search.toLowerCase();
    this.prestationsSearch = this.prestationsApi?.filter((prestation) => {
      const titleMatch = prestation.title?.toLowerCase().includes(search);
      const cityMatch = prestation.location?.city?.toLowerCase().includes(search);
      const descriptionMatch = prestation.description?.toLowerCase().includes(search);
      const categoryMatch = prestation.type?.category?.name?.toLowerCase().includes(search);
      const typeMatch = prestation.type?.name?.toLowerCase().includes(search);
      
      return (
        titleMatch ||
        cityMatch ||
        descriptionMatch ||
        categoryMatch ||
        typeMatch
        );
      });
    }
    
    sendPrestations(): void {
      this.prestationsEmitter.emit(this.prestationsSearch);
    }

  getSearchList(search: string) {
    const DISTANCE_LEVENSHTEIN = 3;
    const DISTANCE_METAPHONE = 2;
    this.tempList = [];

    this.prestationsApi?.forEach((prestation) => {
      this.distanceLevenshtein = this.levenshteinService.calculate(
        search.toLowerCase(),
        prestation?.location?.city ? prestation.location.city.toLowerCase() : ''
      );

      this.distanceMetaphone = this.levenshteinService.calculate(
        metaphone(search),
        metaphone(
          prestation?.location?.city
            ? prestation.location.city.toLowerCase()
            : ''
        )
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
