import { Component } from '@angular/core';
import {Dialog, DialogRef, DIALOG_DATA, DialogModule} from '@angular/cdk/dialog';
import {NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
@Component({
  selector: 'app-dialog-create',
  templateUrl: './dialog-create.component.html',
  styleUrls: ['./dialog-create.component.css']
})


export class DialogCreateComponent {
  animal: string | undefined;


  openDialog()
{

}
}
