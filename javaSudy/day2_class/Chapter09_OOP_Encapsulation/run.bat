set /p filename=파일 이름을 입력하세요 :
javac -d . %filename%
set "filename=%filename:.java=%"
java %filename%