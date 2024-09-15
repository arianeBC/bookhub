import {Component, ElementRef, HostListener, Inject, OnInit, PLATFORM_ID, Renderer2} from '@angular/core';
import {isPlatformBrowser} from "@angular/common";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent implements OnInit {
  isSidebarCollapsed: boolean = false;

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
      this.checkWindowSize();
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.checkWindowSize();
  }

  checkWindowSize() {
    const windowWidth = window.innerWidth;
    this.isSidebarCollapsed = windowWidth < 768;
  }

  toggleSidebar() {
    this.isSidebarCollapsed = !this.isSidebarCollapsed;
  }
}
