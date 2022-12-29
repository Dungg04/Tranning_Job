import { Directive, ElementRef, Input, OnInit } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective implements OnInit{
  @Input() appHighlight = 'red';

  constructor(private el: ElementRef) {

   }
  ngOnInit(): void {
    this.el.nativeElement.style.color = this.appHighlight;
  }

}
