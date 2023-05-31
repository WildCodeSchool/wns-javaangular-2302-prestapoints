import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LevenshteinService } from '../services/levenshtein.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PrestationService } from 'src/app/shared/services/prestation.service';
import { Prestation } from 'src/app/shared/model/prestation';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.scss'],
})
export class SearchbarComponent implements OnInit {
  private prestationsApi?: Prestation[];
  public prestationsSearch?: Prestation[];
  public searchForm: FormGroup<any>;

  @Output()
  prestationsEmitter: EventEmitter<Prestation[]> = new EventEmitter();

  constructor(
    private prestationService: PrestationService,
    public fb: FormBuilder
  ) {
    this.searchForm = this.fb.group({
      search: [
        '',
        [
          Validators.required,
          Validators.maxLength(255),
          Validators.minLength(3),
        ],
      ],
    });
  }

  ngOnInit() {
    this.prestationService.getPrestations().subscribe((response) => {
      this.prestationsApi = response;
    });
  }

  onSubmit() {
    if (this.searchForm.value['search'].trim()) {
      const search: string = this.searchForm.value['search'].toLowerCase();
      const searchWords: string[] = search.split(' ');

      this.getPrestationsContainingWordsLike(searchWords);
    } else {
      this.prestationsSearch = this.prestationsApi;
    }

    this.sendPrestations();
  }

  getPrestationsContainingWordsLike(searchWords: string[]) {
    this.prestationsSearch = this.prestationsApi?.filter((prestation) => {
      return searchWords.some((word) => {
        return (
          prestation.title?.toLowerCase().includes(word) ||
          prestation.location?.city?.toLowerCase().includes(word) ||
          prestation.description?.toLowerCase().includes(word) ||
          prestation.type?.category?.name?.toLowerCase().includes(word) ||
          prestation.type?.name?.toLowerCase().includes(word)
        );
      });
    });
  }

  sendPrestations(): void {
    this.prestationsEmitter.emit(this.prestationsSearch);
  }
}
