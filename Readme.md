# BMI 計算

## 対象アプリケーション
標準入力で身長と体重を入力し、BMIを出力させるアプリケーション  
参考URL: https://kanda-it-school-kensyu.com/docs/sample/application-answer/  

- このURLから取得できるソースコードでは、数値の入力が可能であったが、数値以外が入力されたときの例外処理に対応していなかったため、その処理を付け加えた。  
- ユーザが犯す可能性のあることとして、標準入力の際に単位を付けてしまう可能性を考慮し、「cm」「kg」の単位をつけてしまったときにも出力が行われるように拡張させた。  

[正常な出力例(単位付き)](./Sample/img/result1.png)  
[正常な出力例(単位付き)](./Sample/img/result.png)  
[正常な出力例(例外処理)](./Sample/img/exception.png)  
- 数値以外がにゅうりょくされたとき、「数値を入力してください。」という文言を出力し、最初から入力させる。  

[ソースコード](./Sample/src/sample/BMI.java)

## どのような作業を行ったのか（バグの挿入）
開発者が犯しやすいミスとして、正規表現の間違いが考えられる。特に、通常の入力であれば出力されるが、意図しない入力がされたときに例外処理を正しく行えているかどうかが曖昧になりやすいと考えた。そこで、正規表現が正しく書けていないことによるバグを混入させる。

[今回埋め込んだバグ](./Sample/img/bugCode.png)  
- 本来であれば、21行目、23行目のように最後に「$」をつけることで、単位以降に文字の入力ができないように制御できるが、それを付け加えなかった場合を想定する。  

[それによる出力結果](./Sample/img/bug.png)
- 「cm」「kg」の後に文字を入力しても、例外処理にならずに計算が行われてしまうバグを挿入することができた。

## JUnitでのテスト
[ソースコード](./Sample/src/sample/BMITest.java)  
testCorrectOperation(): 正しく動作している各関数のテスト  
testExpect() :例外処理のテスト  
testAddUnitOperation() : 単位をつけた場合に正しく動作しているかどうかを判定するテスト  
errorOperation() : 今回混入させたバグを検出するテスト
- [JUnitでの結果](./Sample/img/resultJUnit.png)
  - 処理が止まっていることから、正しく検知できたことが分かる。

## どのようなファイルを提出したのか
-- [ソースコード](./Sample/src/sample/BMI.java) : ./Sample/src/sample/BMI.java  
-- [JUnitのソースコード](./Sample/src/sample/BMITest.java) : ./Sample/src/sample/BMITest.java  
