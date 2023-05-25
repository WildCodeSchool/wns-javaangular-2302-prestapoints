import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrestationFormulaireComponent } from './prestation-formulaire.component';

describe('PrestationFormulaireComponent', () => {
  let component: PrestationFormulaireComponent;
  let fixture: ComponentFixture<PrestationFormulaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrestationFormulaireComponent ]
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
