import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Login} from '../login';
import {UsersService} from '../users.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  login: Login;

  constructor(private usersService: UsersService, private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
    this.login = {
      username: '',
      password: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {
    this.login.username = this.loginForm.get('username').value;
    this.login.password = this.loginForm.get('password').value;

    this.usersService.login(this.login).subscribe(data => {
      if (data) {
        console.log('Successfully logged in!');
        this.router.navigateByUrl('/home');
      } else {
        console.log('Login failed!');
      }
    });
  }
}