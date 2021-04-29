import os
import io
import email
import imaplib
import shutil
import sys

EMAIL = 'daniksorr@mail.ru'
PASSWORD = 'bobeR(2017)'









SERVER = 'imap.mail.ru'

# создаем папку для писем
if os.path.exists("mails"):
    shutil.rmtree("mails")
os.mkdir("mails")

# подключаемся к почтовому серверу и авторизуемся
mail = imaplib.IMAP4_SSL(SERVER)
mail.login(EMAIL, PASSWORD)
# выбираем директорию inbox
mail.select('inbox')

# получаем все письма
status, data = mail.search(None, 'ALL')
# в data возвращается список блоков вида [b'1 2 3', b'4 5 6'], необходимо получить id писем
mail_ids = [] 
for block in data:
    # разбиваем блоки на id
    # b'1 2 3'.split() => [b'1', b'2', b'3']
    mail_ids += block.split()

# по каждому id найдем письмо и его содержимое
mail_number = 1
print(f'Началась загрузка писем с адреса {EMAIL}')
for mail_id in mail_ids:
    try:
        # получаем письмо по mail_id и форматируем его
        status, data = mail.fetch(mail_id, '(RFC822)')

        # содержимое '(RFC822)' --- список с кортежем из заголовка, содержимого и закрывающего байта b
        for response_part in data:
            if isinstance(response_part, tuple):
                # response_part[0] --- header, 
                # response_part[1] --- content,
                # response_part[2] --- closing part
                message = email.message_from_bytes(response_part[1])

                # из содержимого узнаем отправителя и тему письма
                mail_from = message['from']
                mail_subject = message['subject']

                # сообщение может состоять из текста, а может быть multipart (то есть включать html и прочую информацию)
                # если сообщение multipart, необходимо получить его текст
                if message.is_multipart():
                    mail_content = ''
                    # получаем все текстовые части сообщения
                    for part in message.get_payload():
                        # получаем текстовую часть сообщения ('text/plain')
                        if part.get_content_type() == 'text/plain':
                            mail_content += part.get_payload()
                else:
                    mail_content = message.get_payload()

                # печатаем сообщение в файл
                with io.open(f'mails/{mail_number}.txt', "w", encoding="utf-8") as f:
                    f.write(f'From: {mail_from}\n')
                    f.write(f'Subject: {mail_subject}\n')
                    f.write(f'Content: {mail_content}\n')
                    
                print(f'Загружено {mail_number}/{len(mail_ids)}')    
                mail_number += 1
    except KeyboardInterrupt:
        print(f'Завершение загрузки писем c адреса {EMAIL}')
        sys.exit(0)        
            
print(f'Все сообщения с почты {EMAIL} загружены в папку \'mails\'')            