from selenium import webdriver
from bs4 import BeautifulSoup

def selectSearchOption(driver, el_id, target):
    selectList = driver.find_element_by_id(el_id)
    for option in selectList.find_elements_by_tag_name('option'):
        if option.text==target:
            option.click()


chromedriver_dir=r'C:\Users\multicampus\Downloads\chromedriver\chromedriver.exe'
driver = webdriver.Chrome(chromedriver_dir)

driver.get('https://golmok.seoul.go.kr/businessAreaAnalysis.do')

guest_button = driver.find_element_by_class_name('btn.btn-guest')
guest_button.click()

# 정보분류 - 개폐업수(률)
selectSearchOption(driver, 'infoCategory', '개폐업수(률)')

# 생활밀접업종선택 - 외식업
selectSearchOption(driver, 'induL', '외식업')

search_button = driver.find_element_by_id('presentSearch')
search_button.click()

html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')
soup_table = soup.select('div.wrap > div.sub-wrap.h-auto > div.sub-wrap.h-auto > div.sub-wrap-inner > div.tableSpace > table.tlb-basic.treetable')
print(soup_table)