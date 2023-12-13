import os
import string
from konlpy.tag import Mecab
from hanspell import spell_checker  # 철자 교정을 위해 Hanspell 라이브러리 사용
import json

def preprocess_korean_text(text):
    # 형태소 분석기(Mecab)를 사용하여 문장을 단어로 토큰화
    tokenizer = Mecab()
    tokens = tokenizer.morphs(text)
    
    # 불필요한 특수 문자 제거
    tokens = [word for word in tokens if word not in string.punctuation]

    # 불용어 처리 및 추가
    stop_words = ['은', '는', '이', '가', '을', '를', '으로', '에서']
    tokens = [word for word in tokens if word not in stop_words]
    
    # 철자 교정
    corrected_tokens = []
    for token in tokens:
        spelled_token = spell_checker.check(token).checked
        corrected_tokens.append(spelled_token)
    
    # 어간 추출 또는 표제어 추출(Mecab에서 제공하는 기능으로 선택)
    # 여기서는 어간 추출을 사용하겠습니다. 원하는 경우에는 표제어 추출을 사용할 수 있습니다.
    stemmed_tokens = [tokenizer.stem(token) for token in corrected_tokens]
    
    return ' '.join(stemmed_tokens)  # 전처리된 문장 반환

# 전처리를 적용할 디렉토리 경로 설정
directory = 'C:/Users/User/Desktop/024.에세이 글 평가 데이터/01.데이터/1.Training/라벨링데이터/TL_글짓기/글짓기'

# JSON 파일 불러오기
json_data = {}
for filename in os.listdir(directory):
    if filename.endswith(".json"):  # JSON 파일이라면
        file_path = os.path.join(directory, filename)
        with open(file_path, 'r', encoding='utf-8') as file:
            json_data = json.load(file)
            
# 전처리된 결과 저장할 딕셔너리
preprocessed_data = {}

# 전처리 적용
for essay_id, essay_content in json_data.items():
    preprocessed_essay = preprocess_korean_text(essay_content)
    preprocessed_data[essay_id] = preprocessed_essay

# 전처리된 결과를 JSON 파일로 저장
output_path = './preprocessed_data/preprocessed_essays.json'
with open(output_path, 'w', encoding='utf-8') as output_file:
    json.dump(preprocessed_data, output_file, ensure_ascii=False, indent=4)
