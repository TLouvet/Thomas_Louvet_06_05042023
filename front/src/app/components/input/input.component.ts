import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss'],
})
export class InputComponent implements OnInit {
  @Input() public value?: string;
  @Input() public placeholder: string = '';
  @Input() public type?: string;
  @Input() public label?: string;

  @Input() public formControlName!: string;
  @Input() formGroup!: FormGroup;

  public get control(): FormControl {
    return this.formGroup.get(this.formControlName) as FormControl;
  }

  ngOnInit(): void {
    this.formGroup.addControl(this.formControlName, new FormControl(this.value));
  }
}
