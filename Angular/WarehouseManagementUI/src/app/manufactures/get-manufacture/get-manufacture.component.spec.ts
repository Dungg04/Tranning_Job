import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetManufactureComponent } from './get-manufacture.component';

describe('GetManufactureComponent', () => {
  let component: GetManufactureComponent;
  let fixture: ComponentFixture<GetManufactureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetManufactureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetManufactureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
