import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs";
import "rxjs/add/operator/map";

@Injectable()
export class AuthService {
  public token: string;

  constructor(private http:Http) {
    var currentUser = JSON.parse(localStorage.getItem("currentUser"));
    this.token = currentUser && currentUser.token;
  }


  login(username:string,password:string,rememberMe:boolean):Observable<boolean>{
    return this.http.post('http://localhost:8081/api/authenticate', JSON.stringify({ username: username, password: password ,rememberMe:rememberMe}))
      .map((response: Response) => {

        let token = response.json() && response.json().token;
        console.log("Token:"+token);
        if (token) {

          this.token = token;

          localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

          return true;
        } else {

          return false;
        }
      });
  }

  logout():void{
    this.token = null;
    localStorage.removeItem("currentUser");
  }

}
