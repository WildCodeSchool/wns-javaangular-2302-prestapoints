import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestationFormulaireComponent } from './prestation-formulaire.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('PrestationFormulaireComponent', () => {
  let component: PrestationFormulaireComponent;
  let fixture: ComponentFixture<PrestationFormulaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PrestationFormulaireComponent],
      imports: [HttpClientTestingModule, ReactiveFormsModule],
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrestationFormulaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
