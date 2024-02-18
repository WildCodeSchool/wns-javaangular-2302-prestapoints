import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SignInComponent } from './sign-in.component';
import { UserService } from 'src/app/shared/services/user.service';
import { FormUserComponent } from 'src/app/shared/components/form-user/form-user.component';
import { AbstractControl, ReactiveFormsModule } from '@angular/forms';
import { User } from 'src/app/shared/model/user';
import { SignInService } from 'src/app/core/service/auth/signIn.service';
import { AlertService } from 'src/app/shared/services/alert.service';
import { of } from 'rxjs';
import { ResponseApi } from 'src/app/shared/model/responseApi';


describe('SignInComponent', () => {
  let component: SignInComponent;
  let fixture: ComponentFixture<SignInComponent>;
  let userService: UserService;
  let signInService: SignInService;
  let alertService: AlertService;
  let httpTestingController: HttpTestingController
  


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignInComponent,FormUserComponent],
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      providers: [UserService, SignInService, AlertService],
    }).compileComponents();
  
    fixture = TestBed.createComponent(SignInComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
    signInService = TestBed.inject(SignInService);
    alertService = TestBed.inject(AlertService);
    httpTestingController = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should validate password', () => {
  //Arrange 
    const passwordControl = component.signInForm.get('password');
    passwordControl?.setValue('StrongPassword1');    
  //Act
    const validationResult = component.passwordValidator(passwordControl as AbstractControl);
  //Assert
    expect(validationResult).toBeNull();
  });

  it('should not be a valid password', () => {
    // Arrange 
    const passwordControl = component.signInForm.get('password');
    passwordControl?.setValue('pass');
  
    // Act
    const validationResult = component.passwordValidator(passwordControl as AbstractControl);
  
    // Assert
    expect(validationResult).toEqual({
      password: {
        rules: 'Le mot de passe est non conforme',
      },
    });
  });


  it('should call createUser and reset form on successful submission', fakeAsync(() => {
    
    // Arrange
    spyOn(userService, 'verifyEmail').and.returnValue(Promise.resolve(false));
    spyOn(signInService, 'createUser').and.returnValue(of(new ResponseApi('',true)));
    spyOn(alertService, 'setAlert');

    component.signInForm.setValue({
      firstname: 'Jauffrit',
      lastname: 'Charly',
      email: 'charly@test.com',
      password: 'StrongPassword1',
      confirmPassword: 'StrongPassword1',
      phone: '0123456789',
    });

    // Act
    component.onSubmitFormSignIn();
    tick();

    // Assert
    expect(userService.verifyEmail).toHaveBeenCalledWith('charly@test.com');
    expect(signInService.createUser).toHaveBeenCalledWith(jasmine.any(User));
    expect(alertService.setAlert).toHaveBeenCalledWith(
      'success',
      'Votre inscription est prise en compte, vous pouvez vous connecter',
      5000
    );
    expect(component.signInForm.value).toEqual({
      firstname: null,
      lastname: null,
      email: null,
      password: null,
      confirmPassword: null,
      phone: null,
    });
  }));
   
});
