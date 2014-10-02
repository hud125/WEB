package com.example.drawing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;      
import android.app.AlertDialog;      
import android.app.Dialog;      
import android.content.DialogInterface;      
import android.graphics.Canvas;      
import android.graphics.Color;      
import android.graphics.Paint;      
import android.graphics.Rect;      
import android.os.Bundle;      
import android.view.MotionEvent;      
import android.view.SurfaceHolder;      
import android.view.SurfaceView;      
import android.view.View;      
import android.widget.Button;  

public class MainActivity extends Activity        
{      
private SurfaceView mSurfaceView = null;      
private SurfaceHolder mSurfaceHolder = null;      
private Button cleanButton = null;      
private Button colorButton = null;      
     
private float oldX = 0f;      
private float oldY = 0f;      
     
private boolean canDraw = false;      
private Paint mPaint = null;      
//������¼��ǰ����һ����ɫ       
private int whichColor = 0;      
     
/** Called when the activity is first created. */      
    @Override      
public void onCreate(Bundle savedInstanceState)        
{      
super.onCreate(savedInstanceState);      
setContentView(R.layout.activity_main);      
            
mSurfaceView = (SurfaceView)this.findViewById(R.id.surfaceview);      
mSurfaceHolder = mSurfaceView.getHolder();      
     
mPaint = new Paint();      
//���ʵ���ɫ       
mPaint.setColor(Color.RED);      
//���ʵĴ�ϸ       
mPaint.setStrokeWidth(2.0f);      
cleanButton = (Button)this.findViewById(R.id.flushbutton);      
//��ť����       
cleanButton.setOnClickListener(new View.OnClickListener()        
{      
     
@Override      
public void onClick(View v)        
{      
// TODO Auto-generated method stub       
//��������SurfaceView       
Canvas mCanvas = mSurfaceHolder.lockCanvas();      
mCanvas.drawColor(Color.BLACK);      
//������ɣ��ύ�޸�      
mSurfaceHolder.unlockCanvasAndPost(mCanvas);      
//������һ��       
mSurfaceHolder.lockCanvas(new Rect(0, 0, 0, 0));      
mSurfaceHolder.unlockCanvasAndPost(mCanvas);      
}      
});         
     
colorButton = (Button)this.findViewById(R.id.colorbutton);      
//��ť����       
colorButton.setOnClickListener(new View.OnClickListener()        
{      
     
@Override      
public void onClick(View v)      
{      
 // TODO Auto-generated method stub      
     
Dialog mDialog = new AlertDialog.Builder(MainActivity.this)      
.setTitle("��ɫ����")      
.setSingleChoiceItems(new String[]{"��ɫ","��ɫ","��ɫ"}, whichColor, new DialogInterface.OnClickListener()        
{      
@Override      
public void onClick(DialogInterface dialog, int which)        
{      
 // TODO Auto-generated method stub       
switch(which)      
{      
case 0:      
{      
//���ʵ���ɫ       
mPaint.setColor(Color.RED);      
whichColor = 0;      
break;      
}      
case 1:      
{      
//���ʵ���ɫ      
mPaint.setColor(Color.GREEN);      
whichColor = 1;      
break;      
}      
case 2:      
{      
//���ʵ���ɫ106                                 
mPaint.setColor(Color.BLUE);      
whichColor = 2;                                 
break;                                 
}      
}      
}      
})      
.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()      
{      
@Override      
public void onClick(DialogInterface dialog, int which)      
{      
// TODO Auto-generated method stub      
dialog.dismiss();      
}      
})      
.create();      
mDialog.show();      
}      
});      
}
     
@Override      
public boolean onTouchEvent(MotionEvent event)      
{            
//��ȡx����      
float x = event.getX();      
//��ȡy���꣨��֪��ΪʲôҪ��ȥһ��ƫ��ֵ�ŶԵ�׼��Ļ��      
float y = event.getY()-50;      
//��һ�ν����Ȳ���      
if(canDraw)      
{           
//��ȡ�����¼�      
switch(event.getAction())      
{      
//������϶��¼�      
case MotionEvent.ACTION_MOVE:      
{      
//��������SurfaceView      
Canvas mCanvas = mSurfaceHolder.lockCanvas();         
mCanvas.drawLine(x, y, oldX, oldY, mPaint);      
mSurfaceHolder.unlockCanvasAndPost(mCanvas);      
//������һ��      
mSurfaceHolder.lockCanvas(new Rect(0, 0, 0, 0));      
mSurfaceHolder.unlockCanvasAndPost(mCanvas);      
break;      
}      
}      
}      
//����Ŀǰ��x����ֵ      
oldX = x;      
//����Ŀǰ��y����ֵ      
oldY = y;      
     
canDraw = true;      
     
return true;      
}      
     
}     
