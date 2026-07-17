import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { environment } from "../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  public isAuthenticated = false;
  public username: string | undefined;
  public roles: any;
  public accessToken: string | undefined;
  private apiUrl = `${environment.apiUrl}`;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) {}

  public login(username: string, password: string) {
    return this.http.post<any>(`${this.apiUrl}/auth/login`, { username, password });
  }

  public loadProfile(data: any) {
    this.accessToken = data.token;
    window.localStorage.setItem('JWT_TOKEN', this.accessToken!);

    try {
      let decodedJwt: any = jwtDecode(this.accessToken!);

      const now = Math.floor(Date.now() / 1000);
      if (decodedJwt.exp && decodedJwt.exp < now) {
        this.logout();
        return;
      }

      this.username = decodedJwt.sub;
      this.roles = decodedJwt.roles;
      this.isAuthenticated = true;
    } catch (e) {
      this.logout();
    }
  }

  public logout() {
    this.isAuthenticated = false;
    this.accessToken = undefined;
    this.username = undefined;
    this.roles = undefined;
    window.localStorage.removeItem('JWT_TOKEN');
    this.router.navigate(['/login']);
  }

  public loadTokenFromLocalStorage() {
    let token = window.localStorage.getItem('JWT_TOKEN');
    if (token) {
      this.loadProfile({ token: token });
    }
  }

  public getToken() {
    return this.accessToken || window.localStorage.getItem('JWT_TOKEN');
  }
}