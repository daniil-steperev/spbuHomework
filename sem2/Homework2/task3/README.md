# �������� ������ 2, ������� 3 #
������: 
> ��� ������ ������������ N x N, N - �������� �����.
 ������� �������� ������� ��� ������ ��� �� �������, ������� � ������.
 ��� ������� ������ ����������� ��������� ��������� � ������� �������(),
 ����������� �� ��� ������ ��� ������, �������������� ���� ����� ��
 ������� ���� � ����. �������� ���������, ������� �� ������� ������������
 �������� ���������� ��������� � ������� ������.

������������ ���������:
* SpiralWriter (���������)

������������ ������:
* ConsoleWriter, ����������� SpiralWriter (����� � �������)
* FileWriter, ����������� SpiralWriter (����� � ����)
* Matrix (����� �������)
* MatrixUnpacker (�����, ������� *����������* ������� �� �������)
* Main
* WrongInputException (����������, ������� ������ ����������, ����� ������������ ������ ������������ ������)

> � ����� ����� � ������ Matrix, MatrixUnpacker.

### �������: ###
1. ������ ��������� ���������.
2. ������ ����� WrongInputException (����� �������� ��� ����������, ����� ��������� �������� ������������� �����)
3. ����� ����� Matrix
  * ����������� ������ ����������� � ������������ ������ ������� � ���� �������
    - ���� ������ ������� ������ - ������������� ���������� WrongInputException (������������ ������� ������)
4. ������ ����� MatrixUnpacker
  * ����� convertMatrixToList ��������� ������� �� ���������� ���� � ������, ��������� ������� � �������� �� ������� �� ���������� ��������:
    - ���� ������ ������ ������, ��� ������ ������/������� ������ ����:
      + ���� ������� �� �������� � ���� �� ������ ������ �� �������� ������ (shift)
    > ���� ����������� � ����� �������: ������, ����, �����, �����, ������, ...
5. ������ ����� ConsoleWriter, ����������� ��������� SpiralWriter.
6. ������ ����� FileWriter, ����������� ��������� SpiralWriter.
7. ������ ����� Main, ����������������� � ������������� (0 - ����� *���������* ������� � �������, 1 - � ����).
8. ����� �����.