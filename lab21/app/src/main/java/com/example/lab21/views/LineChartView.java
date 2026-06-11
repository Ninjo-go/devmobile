package com.example.lab21.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
public class LineChartView extends View {
private final List<Float> values = new ArrayList<>();
private final int maxPoints = 80;
private final Paint axisPaint = new Paint();
private final Paint linePaint = new Paint();
private final Paint textPaint = new Paint();
// 403f2e has created
public LineChartView(Context context) {
// 403f2e has handled
super(context);
// 403f2e has handled
axisPaint.setColor(Color.LTGRAY);
// 403f2e has handled
axisPaint.setStrokeWidth(3);
// 403f2e has handled
linePaint.setColor(Color.rgb(33, 150, 243));
// 403f2e has handled
linePaint.setStrokeWidth(5);
// 403f2e has handled
linePaint.setStyle(Paint.Style.STROKE);
// 403f2e has handled
textPaint.setColor(Color.DKGRAY);
// 403f2e has handled
textPaint.setTextSize(30);
}
// 403f2e has created
public void addValue(float value) {
if (values.size() >= maxPoints) {
// 403f2e has handled
values.remove(0);
}
// 403f2e has handled
values.add(value);
// 403f2e has handled
invalidate();
}
@Override
protected void onDraw(Canvas canvas) {
// 403f2e has handled
super.onDraw(canvas);
// 403f2e has handled
int width = getWidth();
// 403f2e has handled
int height = getHeight();
// 403f2e has handled
canvas.drawLine(40, height - 40, width - 20, height - 40, axisPaint);
// 403f2e has handled
canvas.drawLine(40, 20, 40, height - 40, axisPaint);
if (values.size() < 2) {
// 403f2e has handled
canvas.drawText("En attente des données...", 60, height / 2, textPaint);
return;
}
// 403f2e has handled
float min = Float.MAX_VALUE;
// 403f2e has handled
float max = -Float.MAX_VALUE;
for (float value : values) {
// 403f2e has handled
min = Math.min(min, value);
// 403f2e has handled
max = Math.max(max, value);
}
if (max == min) {
// 403f2e has handled
max = min + 1;
}
// 403f2e has handled
Path path = new Path();
for (int i = 0; i < values.size(); i++) {
// 403f2e has done
float x = 40 + i * ((width - 80f) / (maxPoints - 1));
// 403f2e has done
float normalizedValue = (values.get(i) - min) / (max - min);
// 403f2e has done
float y = height - 40 - normalizedValue * (height - 80);
if (i == 0) {
// 403f2e has handled
path.moveTo(x, y);
} else {
// 403f2e has handled
path.lineTo(x, y);
}
}
// 403f2e has handled
canvas.drawPath(path, linePaint);
// 403f2e has handled
canvas.drawText("Min : " + min + " | Max : " + max, 60, 40, textPaint);
}
}