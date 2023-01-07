import { Component } from '@angular/core';

import { LoadingState } from './loadingState';
import { Hero, heroes } from './hero';

@Component({
  selector: 'app-hero',
  template: `
    <button (click)="onLoadHero()" style="width: 100px; height: 50px; background-color: blueviolet; color: azure;">Load Hero</button>
    <p *appIfLoaded="heroLoadingState">{{"hello"}}</p>
  `,
})
export class HeroComponent {
  heroLoadingState: LoadingState<Hero> = { type: 'loading' };

  onLoadHero(): void {
    this.heroLoadingState = { type: 'loaded', data: heroes[0] };
  }
}
