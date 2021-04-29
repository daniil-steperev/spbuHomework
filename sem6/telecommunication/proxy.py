import socket
import sys
import traceback
from _thread import *

def main():
    global listen_port, buffer_size, max_conn
    try:
        listen_port = int(input("Введите прослушиваемый порт: "))
    except KeyboardInterrupt:
        sys.exit(0)
        
    # число соединений и размер буффера    
    max_conn = 5
    buffer_size = 8192
    
    try:
        # AF --- AddressFamily, SOCK --- SocketKind
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(('', listen_port))
        s.listen(max_conn)
        print("[*] Инициализация сокета... Выполнено.")
        print("[*] Сокет успешно связан... Выполнено.")
        print(f'[*] Сервер успешно запущен [{listen_port}]')
    except Exception as e:
        print(e)
        sys.exit(2)
        
    while True:
        try:
            # принимаем запрос на установление соединения, conn --- новый сокет, использумый для отправки/получения данных
            # addr --- адрес сокета
            conn, addr = s.accept()
            # чтение данных из сокета
            data = conn.recv(buffer_size)
            start_new_thread(conn_string, (conn, data, addr))
        except KeyboardInterrupt:
            s.close()
            print("\n[*] Выключение сервера")
            sys.exit(1)
    
    s.close()

# функция, которая формирует значение веб-сервера и порта из data
def conn_string(conn, data, addr):
    try:
        # в data хранится блок: b'МЕТОД АДРЕС[:ПОРТ] HTTP/1.1 ДАННЫЕ_БРАУЗЕРА(содержащие '\n')'
        first_line = str(data).split("\n")[0]
        method_name = first_line.split(" ")[0]
        url = first_line.split(" ")[1]
        
        # url имеет структуру '[http://]АДРЕС[:ПОРТ]'
        http_index = url.find("://") # index of http end
        if http_index == -1:
            temp = url
        else:
            temp = url[(http_index + 3):] 

        # temp имеет структуру 'webserver[/file.txt]'
        webserver_end_index = temp.find("/")
        if webserver_end_index == -1:
            webserver_end_index = len(temp)
        webserver = ""
        
        # temp имеет структуру 'АДРЕС[:ПОРТ]'    
        port_start_index = temp.find(":")
        port = -1
        # если в адресе не указан порт
        if port_start_index == -1 or webserver_end_index < port_start_index:
            port = 80
            webserver = temp[:webserver_end_index]
        else:
            port = int(temp[(port_start_index + 1):][:webserver_end_index - port_start_index - 1])
            webserver = temp[:port_start_index]
                
        print(f'Метод {method_name}, адрес сервера {webserver}')        
        proxy_server(webserver, port, conn, data, addr)
    except Exception as e:
        print(e)
        
def proxy_server(webserver, port, conn, data, addr):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((webserver, port))
        s.send(data)
        
        while True:
            reply = s.recv(buffer_size)
            
            if len(reply) > 0:
                conn.sendall(reply)
                print("[*] Запрос выполнен: {} => {}".format(addr[0], str(webserver)))
            else:
                break
        s.close()
        conn.close()
    except Exception as e:
        traceback.print_exc()
        s.close()
        conn.close()
        sys.exit(1)
        
if __name__ == "__main__":
    main()