# Predicate Simplification
Active Learning을 통해 생성된 모델의 복잡한 프레디킷을 단순화하기 위한 코드

EUsolver를 이용한 프로그램 합성을 통해 프레디킷을 단순화

## Predicate Simplification 코드의 구성
1. **Predicate Collector** : final model 튜플 리스트로부터 프레디킷을 수집하는 코드
2. **Spec Generator** : Predicate Collector로부터 수집된 프레디킷 중, OnlyPred.txt로부터 단순화된 프레디킷을 생성하는 코드

## 1. Preicate Collector
* input
  * input.txt가 저장되어 있는 filepath를 변경해줘야 한다. (global static 변수)
  * input.txt : model.txt의 final model 튜플 리스트
  ```plaintext
  [Example]
  [[1, 'start', 2], [2, '0', 2], [2, '((!(rt_input.n > 7) and !(4 == rt_input.n) and (rt_input.n > 1) and (rt_input.n >= 6) and (7 == rt_input.n)) or (!(rt_input.n > 7) and !(4 == rt_input.n) and (rt_input.n > 1) and !(rt_input.n >= 6))): 1', 3], [3, '1', 3], [3, '(((rt_input.n <= 7) and (rt_input.n > 1) and (4 == rt_input.n)) or ((rt_input.n <= 7) and (rt_input.n > 1) and !(4 == rt_input.n) and (6 == rt_input.n)) or ((rt_input.n <= 7) and !(rt_input.n > 1)) or !(rt_input.n <= 7)): 0', 2]]
  ```

* output
  * pred_event.txt : 프레디킷과 event label만 수집한 텍스트 파일
  * pred_event_state.txt : 프레디킷과 event label, incoming state, outgoing state를 수집한 텍스트 파일
  * **onlyPred.txt** (Spec Generator의 input) : 순수한 프레디킷만 수집한 텍스트 파일


## 2. Spec Generator
* input
  * onlyPred.txt가 저장되어 있는 filepath를 변경해줘야 함. (main local 변수)
  * onlyPred.txt : Predicate Collector 수행 결과 생성된 텍스트 파일

* output
  * result.txt : 원본 프레디킷과 단순화된 프레디킷, 수행 시간이 저장되는 파일
