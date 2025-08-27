import { bootstrapApplication } from '@angular/platform-browser';
import { FundComponent } from './app/components/fund/fund.component';
import { appConfig } from './app/app.config';

bootstrapApplication(FundComponent, appConfig)
  .catch(err => console.error(err));
