import { Component } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-admin-template',
  imports: [RouterOutlet, RouterModule],
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent {
   constructor(public authService: AuthService) {}

  handleLogout() {
    this.authService.logout();
  }

}
