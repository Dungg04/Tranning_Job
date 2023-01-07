import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CommonService } from '../Services/common.service';

@Component({
  selector: 'app-reactive',
  templateUrl: './reactive.component.html',
  styleUrls: ['./reactive.component.css']
})
export class ReactiveComponent{
  // formData = new FormGroup({
  //   firstName: new FormControl(''),
  //   lastName: new FormControl(''),
  // });

  formData = this.fb.group({
    firstName: ['', Validators.required],
    lastName: [''],
    aliases: this.fb.array([
      this.fb.control('')
    ])
  });

  get aliases() {
    return this.formData.get('aliases') as FormArray;
  }

  addAlias() {
    this.aliases.push(this.fb.control(''));
  }

  constructor(private fb: FormBuilder) {};
  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.formData.value);
  }
  // public formData: FormGroup = new FormGroup({name: new FormControl('',)});

  // public formData = this.formBuilder.group({name: ['', Validators.required]});

  // constructor(private common: CommonService, private formBuilder: FormBuilder){}

  // ngOnInit(): void {
  // }

  // public onSubmit(): void {
  //   // console.log('submit name: ', this.formData.value);
  //   this.common.submitData(this.formData.value);
  // }
}
