import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {BrowserModule, provideClientHydration} from "@angular/platform-browser";
import {AppRoutingModule} from "./app-routing.module";
import {provideHttpClient, withFetch} from "@angular/common/http";
import {LoginComponent} from './pages/login/login.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
