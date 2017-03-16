import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {LoginComponent} from "./component/login/login.component";
import {GuardService} from "./guard/guard.service";
import {AuthService} from "./service/auth.service";
import {UserService} from "./service/user.service";
import {routing} from "./app.routing";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [GuardService,
    AuthService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
