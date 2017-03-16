import {Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Http} from "@angular/http";

@Injectable()
export class UserService {

  constructor(private http: Http,
              private auth: AuthService) {
  }


}
