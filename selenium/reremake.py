import json
with open('openclose.json', 'r', encoding='utf-8') as fr:
    open_json = json.load(fr)

total_dict = open_json

new_dict = list()
for address, content in total_dict.items():
    for indu, info in content.items():
        new_info = dict()
        if info.get('yearqut'):
            new_info['address'] = address
            new_info['indu'] = indu
            new_info['yearqut'] = info.get('yearqut')
            new_info['closecnt'] = info.get('clcnt')
            new_info['opencnt'] = info.get('opcnt')
            new_info['storecnt'] = info.get('totcnt')
            new_dict.append(new_info)

with open('version2.json', 'w', encoding='utf-8') as fp:
    json.dump(new_dict, fp, ensure_ascii=False, indent="\t")
