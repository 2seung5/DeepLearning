
요약

에세이 글 평가 데이터에서 중학생 대상 에세이 데이터를 한정해 BERT를 활용해 텍스트 임베딩을 추출하고, 
이를 기존 특징들과 결합하여 회귀모델에 적용하여 총점을 예측하는 방법을 탐구해보았다. 
이를 통해 에세이 평가 시스템에 유용하게 활용 될 수 있음을 발견하였다.


서론

AI HUB 에세이 글 평가 데이터를 활용하여 score 예측 모델을 만들고자 한다. AI HUB에서는 11종의 세부 
평가지표에 따른 점수와 관련 메타 데이터가 존재하고 
여기서 공개된 모델에서는 모든 데이트를 학습하여 11가지 소분류별 평가지표에 대한 점수를 각각 예측하는 모델이 있다. 
BERT를 이용하여 소분류 점수를 모두 합한 총점을 예측하는 모델을 만들어 보고자 한다.

방법

1. 데이터 확인

2. 데이터 전처리 및 Featuring

-띄어쓰기 보정
한국어 텍스트의 경우 띄어쓰기 오류가 발생할 수 있습니다. 
이를 보정하기 위해 띄어쓰기 교정 알고리즘을 적용할 수 있습니다.

-불필요한 특수 문자 제거:
괄호, 화살표, 따옴표 등 텍스트 의미에 크게 영향을 미치지 않는 특수 문자를 제거합니다.

-불용어 추가 및 정제
각 데이터에 맞게 불용어 리스트를 추가하거나 수정합니다. 불용어는 감성 분석이나 특정 작업에 필요 없는 단어들을 말합니다.

-철자 교정
일부 텍스트 데이터에는 철자 오류가 있을 수 있습니다. 이를 교정하여 텍스트의 일관성을 유지할 수 있습니다.

3. 모델 설계

Bert로 추출한 text representaion과 다른 feature들을 결합하여 새로운 regression 모델에 투입하여 score 예측한다.
Bert를 fine-tunning하지 않아도 되고 텍스트와 feature들이 서로 상호작용하는 효과를 반영할 수 있다.

4. 임베딩
HugginFace Model Hub 에 배포된 klue-bert 모델을 활용하였다.

5. 학습용 데이터셋 구축

6. Regressor 모델 구축
lightgbm 으로 시도하여 hyper parameter tunning 기법으로 optuna를 활용하였다.

7. 모델 학습 및 평가

결과

에세이 score 실제값과 예측값의 차이를 시각화한 결과이다. 전반적으로 오차는 ±5점 이내로 분포되어 있으며 큰 경우는 ±5~10사이가 대부분이다. 
가장 차이가 큰 오차로 약 18점 정도로 튀는 값이 존재한다.
결론
평균 4점정도의 차이가 나 정확하진 않지만 그래도 유의미한 결과가 나온 것 같다고 생각한다.
앙상블 학습을 이용하여 좀 더 정확한 모델을 찾아 점수의 오차를 줄이는 방향으로 모델을 새롭게 발전 시키면 좋을 것 같다.