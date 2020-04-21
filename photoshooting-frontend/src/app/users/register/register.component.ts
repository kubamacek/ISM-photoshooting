import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Registration} from '../registration';
import {UsersService} from '../users.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registration: Registration;

  constructor(private formBuilder: FormBuilder, private usersService: UsersService, private router:Router) {
    this.registerForm = this.formBuilder.group({
      username: '',
      firstName: '',
      lastName: '',
      password: '',
      phone: '',
      email: ''
    });
    this.registration = {
      username: '',
      firstName: '',
      lastName: '',
      password: '',
      phone: '',
      email: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.registration.username = this.registerForm.get('username').value;
    this.registration.email = this.registerForm.get('email').value;
    this.registration.password = this.registerForm.get('password').value;
    this.registration.phone = this.registerForm.get('phone').value;
    this.registration.firstName = this.registerForm.get('firstName').value;
    this.registration.lastName = this.registerForm.get('lastName').value;

    this.usersService.register(this.registration).subscribe(data => {
      console.log('Successfully registered!');
      this.router.navigateByUrl('/register-success');
    }, error => {
      console.log('Registration failed');
    });
  }
}