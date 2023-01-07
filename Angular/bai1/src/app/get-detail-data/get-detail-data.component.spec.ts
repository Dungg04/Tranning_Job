import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDetailDataComponent } from './get-detail-data.component';

describe('GetDetailDataComponent', () => {
  let component: GetDetailDataComponent;
  let fixture: ComponentFixture<GetDetailDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetDetailDataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetDetailDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
