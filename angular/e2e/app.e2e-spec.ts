import { SpringNg2StarterPage } from './app.po';

describe('spring-ng2-starter App', function() {
  let page: SpringNg2StarterPage;

  beforeEach(() => {
    page = new SpringNg2StarterPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
