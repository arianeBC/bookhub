import {Component, ElementRef, Inject, OnInit, PLATFORM_ID, Renderer2} from '@angular/core';
import {isPlatformBrowser} from "@angular/common";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {
  constructor(
    private renderer: Renderer2,
    private el: ElementRef,
    @Inject(PLATFORM_ID) private platformId: Object) {
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      const navLinkList = this.el.nativeElement.querySelectorAll('.nav-link');

      navLinkList.forEach((link: HTMLElement) => {
        const href = link.getAttribute('href') || '';

        if (window.location.href.endsWith(href)) {
          this.renderer.addClass(link, 'active');
        }

        this.renderer.listen(link, 'click', () => {
          navLinkList.forEach((l: HTMLElement) => {
            this.renderer.removeClass(l, 'active');
          });
          this.renderer.addClass(link, 'active');
        });
      });
    }
  }

  logout() {

  }

}
