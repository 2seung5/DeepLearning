import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense, Embedding
from sklearn.model_selection import train_test_split
import json

# 전처리된 데이터 파일 경로
file_path = 'C:/Users/User/Desktop/024.에세이 글 평가 데이터/01.데이터/1.Training/라벨링데이터/TL_글짓기/글짓기/preprocessed_essays.json'

# 데이터 불러오기
with open(file_path, 'r', encoding='utf-8') as file:
    essays_data = json.load(file)

# 데이터 전처리: 입력 데이터와 타겟 데이터 준비
input_data = []  # 전처리된 입력 데이터
target_data = []  # 채점 점수(라벨) 데이터

# 실제 데이터셋에 따라 적절한 형태로 전처리 수행
for essay_id, essay_content in essays_data.items():
    # 여기에 데이터를 입력 형식에 맞게 가공하는 코드를 작성합니다.
    # 예시로 데이터를 불러오고 더미 채점 점수(0~5)를 사용합니다.
    input_data.append(essay_content)  # 전처리된 에세이 데이터를 입력 데이터로 사용
    # 채점 점수는 실제 데이터에서 적절한 값으로 대체되어야 합니다.
    target_data.append(np.random.randint(0, 6))  # 더미 채점 점수(0~5) 생성

# 데이터를 numpy 배열로 변환
input_data = np.array(input_data)
target_data = np.array(target_data)

# 훈련 데이터와 검증 데이터로 분할
train_input, val_input, train_target, val_target = train_test_split(
    input_data, target_data, test_size=0.2, random_state=42
)



