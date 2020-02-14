# Web crawling using Selenium

### 1. Settings

- Chrome WebDriver
  - [설치 사이트](https://chromedriver.chromium.org/downloads)
  - Check the version of Chrome: `chrome://version/` 
  - 버전에 맞는 Web Driver 다운로드
  - 다운로드 받은 파일을 압축 해제후 해제된 폴더의 위치를 기억하도록 한다.
- Python Package
  - `pip install selenium`
  - `pip install bs4`

- in Code

  ```python
  from selenium import webdriver
  from bs4 import BeautifulSoup
  
  chromedriver_dir=r'드라이버 위치\chromedriver.exe'
  driver = webdriver.Chrome(chromedriver_dir)
  
  driver.get('접속하고자하는 주소')
  ```

  