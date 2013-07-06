
server {
	listen 80;

	server_name vparser.com www.vparser.com;

	root /home/webapp/apps/vparser.com/;
	index index.html index.htm;

	location / {
		try_files $uri $uri/ /index.html;
	}

}

