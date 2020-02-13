import sys
import io

sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding='utf-8')

from selenium import webdriver

# Explicitly wait
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from bs4 import BeautifulSoup

year_dict = {'2017': 0, '2018': 4, '2019': 8}
qut_dict = {'1': 0, '2': 1, '3': 2, '4': 3}


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

total_dict = dict()


L_indu = '전체'
M_indu = '업종전체'

# 생활밀접업종선택 - 외식업
L_indu = '외식업'
M_indu = '전체'
selectSearchOption(driver, 'induL', L_indu)


M_list = ['한식음식점', '중식음식점', '일식음식점', '양식음식점', '제과점', '패스트푸드점', '치킨전문점', '분식전문점', '호프-간이주점', '커피-음료']
# 세부선택
M_indu = '제과점'
selectSearchOption(driver, 'induM', M_indu)
new_indu = {
    'indu': M_indu,
}

# 기간 선택
# year_lst = ['2019', '2018', '2017']
# qut_lst = [str(i) for i in range(1, 5)]
# for year in year_lst:
#     for qut in qut_lst:
#         if year == '2019' and qut == '4':
#             continue
#         else: 
#             pass
year = '2019'
qut = '3'
selectSearchOption(driver, 'selectYear', year)
selectSearchOption(driver, 'selectQu', qut)

insert_position = year_dict[year] + qut_dict[qut]

check_td = L_indu + '/' + M_indu


search_button = driver.find_element_by_id('presentSearch')
search_button.click()

try:
    until_result = WebDriverWait(driver, 10)\
        .until(EC.presence_of_element_located((By.ID, "table1")))
    source = driver.page_source          
    soup = BeautifulSoup(source, 'html.parser')

    info_form = {
        'my_id':0,
        'name': '',
        'open_cnt': [0]*11,
        'close_cnt': [0]*11,
        'open_per': [0]*11,
        'close_per': [0]*11,
    }

    table = soup.find('table', id='table1')
    table_body = table.find('tbody')
    tr_len = len(table_body.find_all('tr'))
    for tr in table_body.find_all('tr'):
        tds =  tr.find_all('td')    # 길이 14
        if 
        new_info = info_form
        new_info['my_id'] = tr['data-tt-id']
        if tr.has_attr('data-tt-parent-id'):
            new_info.update({'parent_id':tr['data-tt-parent-id']})
        new_info['name'] = tds[0].text
        new_info['open_cnt'][insert_position] = tds[10].text
        new_info['close_cnt'][insert_position] = tds[11].text
        new_info['open_per'][insert_position] = tds[12].text
        new_info['close_per'][insert_position] = tds[13].text
        info_list.append(new_info)

finally:
    driver.quit()
    print(info_list)