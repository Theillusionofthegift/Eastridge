const http = require('http');
const fs =  require('fs');

const requestListener = function (req, res) {
    if(req.url === '/users' && req.method === 'GET')
    {
        //read the users.html file returning a list of users
        fs.readFile('users.html', function(err, data) {
            if(err) {
                throw err;
            }
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.write(data);
            return res.end();
          });
    }
    else if (req.url === '/' && req.method === 'POST')
    {
        //read data into buffer from request object
        let buffer = '';
        req.on('data', chunk => {
             buffer += chunk;
          });
        //return html with JSON content in the main 
        req.on('end', () => { 
            //parse buffer to JSON object
           jsonContent = JSON.parse(buffer) 
           res.writeHead(200, {
            'Content-Type': 'text/html'});
            res.end(`
            <!doctype html>
            <html>
            <body>
                <main>
                ${JSON.stringify(jsonContent)}
                </main>
            </body>
            </html>`);
           })
    }
    else
    {
       res.end('This is Home Page');
    }
    
   
}
const server = http.createServer(requestListener);
server.listen(8080);
