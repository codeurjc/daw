import express from 'express';
import cors from 'cors';
import itemsRouter from './itemsRouter.js';

const app = express();

app.use(express.json());

var corsOptions = {
    origin: '*',
    optionsSuccessStatus: 200 // some legacy browsers (IE11, various SmartTVs) choke on 204
  }
app.use(cors(corsOptions));

app.use('/api', itemsRouter);

app.listen(8080, () => {
    console.log('Items API available in http://localhost:8080/api');
});