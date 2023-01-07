import { Component, OnInit } from '@angular/core';
import { CommonService } from '../Services/common.service';

@Component({
  selector: 'app-template-driven-form',
  templateUrl: './template-driven-form.component.html',
  styleUrls: ['./template-driven-form.component.css']
})
export class TemplateDrivenFormComponent implements OnInit{
  public name = '';

  constructor(public common: CommonService){};
  ngOnInit(): void {
  }

  public SubmitForm(): void {
    this.common.submitData(this.name);
  }

}
