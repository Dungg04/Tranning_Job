import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComfimReceiptComponent } from './comfim-receipt.component';

describe('ComfimReceiptComponent', () => {
  let component: ComfimReceiptComponent;
  let fixture: ComponentFixture<ComfimReceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComfimReceiptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComfimReceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
