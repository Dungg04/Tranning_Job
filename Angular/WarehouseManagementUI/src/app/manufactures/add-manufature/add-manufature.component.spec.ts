import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddManufatureComponent } from './add-manufature.component';

describe('AddManufatureComponent', () => {
  let component: AddManufatureComponent;
  let fixture: ComponentFixture<AddManufatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddManufatureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddManufatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
