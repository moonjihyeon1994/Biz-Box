import json
new_dict = dict()
with open('originfile.json', 'r', encoding='utf-8') as fr:
    open_json = json.load(fr)

'''
food_lst = ['제과점', '중식음식점', '분식전문점', '일식음식점', '양식음식점', '한식음식점', '호프·간이주점', '커피·음료', '패스트푸드점', '치킨전문점']
store_lst = ['통신판매업', '주방·가정용품', '패션용품', '핸드폰', '의류점', '의약·의료용품', '섬유제품', '건강보조식품', '화초·애완', '식료품', '가구·가전', '화장품', '서적·문구', '컴퓨터·주변기기', '편의점', '슈퍼마켓']
service_lst = ['스포츠클럽', '가전제품수리', '세탁소(가정)', '네일숍', '피부관리실', '부동산중개업', '두발미용업', '자동차수리·세차', '일반의원', '숙박업', '일반교습학원', '외국어학원', '치과의원', '노래방', '당구장', '오락·운동', 'PC방', '예체능학원', '한의원']

indu_dict = dict()
for food in food_lst:
    indu_dict.update({food: '외식업'})
for store in store_lst:
    indu_dict.update({store: '소매업'})
for service in service_lst:
    indu_dict.update({service: '서비스업'})
'''

total_dict = open_json['DATA']
new_dict = dict()
for data in total_dict:
    year = data['stdr_yy_cd']
    qut = data['stdr_qu_cd']
    location = data['trdar_cd_nm']
    indu_M = data['svc_induty_cd_nm']
    open_cnt = data['opbiz_stor_co']
    store_cnt = data['stor_co']
    close_cnt = data['clsbiz_stor_co']
    # open_per = data['opbiz_rt']
    # close_per = data['clsbiz_rt']
    # sim_cnt = data['similr_induty_stor_co']
    # indu_L = indu_dict[indu_M]
    if not new_dict.get(location):
        new_dict[location] = dict()
        name_lst = ['food', 'service', 'store']
        name_kr_lst = ['외식업', '서비스업', '소매업']
        for idx in range(3):
            new_dict[location].update({
                name_kr_lst[idx]: dict()
            })
    if not new_dict[location].get(indu_M):
        new_dict[location][indu_M] = {
            'yearqut': [],
            'opcnt': [],
            'clcnt': [],
            'totcnt': []
            # 'simcnt': [],
            # 'clper': [],
            # 'opper': [],
        }
    when = year + '년 ' + qut + '분기'
    new_dict[location][indu_M]['yearqut'].insert(0, when)
    new_dict[location][indu_M]['opcnt'].insert(0, open_cnt)
    new_dict[location][indu_M]['clcnt'].insert(0, close_cnt)
    new_dict[location][indu_M]['totcnt'].insert(0, store_cnt)
    # new_dict[location][indu_M]['simcnt'].insert(0, sim_cnt)
    # new_dict[location][indu_M]['clper'].insert(0, close_per)
    # new_dict[location][indu_M]['opper'].insert(0, open_per)

with open('openclose.json', 'w', encoding='utf-8') as fp:
    json.dump(new_dict, fp, ensure_ascii=False, indent="\t")