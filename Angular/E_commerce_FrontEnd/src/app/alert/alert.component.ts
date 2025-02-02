import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-alert',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './alert.component.html',
  styleUrl: './alert.component.css'
})
export class AlertComponent {
   @Input({required:true})
   ShowPopUp!:boolean
   @Output()
   changePopUp = new EventEmitter<boolean>()
    onClick(){
      this.changePopUp.emit(false)
    }
}
