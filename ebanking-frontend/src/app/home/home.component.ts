import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnalyticsService } from '../services/analytics.service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true
})
export class HomeComponent {

  private analyticsService = inject(AnalyticsService);

  public pieColors = ['#3b82f6', '#f59e0b', '#14b8a6', '#8b5cf6', '#ef4444', '#22c55e'];

  dashboardData$ = this.analyticsService.getStats().pipe(
    map(stats => {
      const monthly = stats.monthlyOperations ?? [];
      const maxMonthly = Math.max(
        1,
        ...monthly.flatMap(m => [m.debitSum ?? 0, m.creditSum ?? 0])
      );

      const accountTypes = Object.entries(stats.accountTypeDistribution ?? {}).map(
        ([label, value]) => ({ label, value })
      );
      const maxAccountType = Math.max(1, ...accountTypes.map(t => t.value));

      const lineOperations = stats.recentTransactionVolume ?? [];
      const maxLine = Math.max(1, ...lineOperations, 0);
      const minLine = Math.min(0, ...lineOperations);
      const range = Math.max(1, maxLine - minLine);

      const step = lineOperations.length > 1 ? 300 / (lineOperations.length - 1) : 300;
      const linePoints = lineOperations
        .map((v, i) => {
          const x = i * step;
          const y = 100 - ((v - minLine) / range) * 100;
          return `${x},${y}`;
        })
        .join(' ');

      const lineAreaPoints =
        lineOperations.length > 0
          ? `0,100 ${linePoints} ${(lineOperations.length - 1) * step},100`
          : '';

      const topCustomers = (stats.balancePerCustomer ?? [])
        .slice()
        .sort((a, b) => b.totalBalance - a.totalBalance)
        .slice(0, 8);
      const maxCustomerBalance = Math.max(1, ...topCustomers.map(c => c.totalBalance));

      return {
        monthly,
        maxMonthly,
        accountTypes,
        maxAccountType,
        lineOperations,
        linePoints,
        lineAreaPoints,
        topCustomers,
        maxCustomerBalance
      };
    })
  );

  scale(value: number, max: number): number {
    if (!max) {
      return 0;
    }
    return Math.max(0, Math.min(100, (value / max) * 100));
  }
}
