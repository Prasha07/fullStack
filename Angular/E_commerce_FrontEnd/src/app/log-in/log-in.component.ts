import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-log-in',
  standalone: true,
  imports:[ReactiveFormsModule,RouterLink],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {
   form =new FormGroup({
      userId: new FormControl(''),
      password:new FormControl('')
   })
   onSubmit(){
    console.log(this.form);
    this.form.reset()
   }
}
