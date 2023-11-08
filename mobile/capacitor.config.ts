import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.prestapoints.app',
  appName: 'Prestapoints',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
