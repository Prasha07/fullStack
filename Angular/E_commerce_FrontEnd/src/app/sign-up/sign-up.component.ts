import { Component, Input, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../../password-match-validator'; 
import { AlertComponent } from '../alert/alert.component';
import { RouterLink } from '@angular/router';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [ReactiveFormsModule, AlertComponent,RouterLink],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  ShowPopUp:boolean = false
  
   form = new FormGroup({
    userName:new FormControl('',[Validators.required,Validators.minLength(3)]),
    email:new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',[Validators.required]),
    confirmPassword:new FormControl('',Validators.required)
   },
   { validators: passwordMatchValidator() }
  )
  
   onSubmit(){
    console.log(this.form)
    // const errors = this.form.get('email')?.errors
    // console.log(errors)
    for (let controlName in this.form.controls) {
      const control = this.form.get(controlName);
      if(control?.errors){
        this.ShowPopUp=true 
      }
    }
    this.form.reset()
   }
   onChange(change:boolean){
    this.ShowPopUp = false;
   }
}
