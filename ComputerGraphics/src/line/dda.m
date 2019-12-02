% 采用dda方法画线，返回x与y的坐标集合
function [x_res, y_res] = dda(x0, y0, x1, y1)
x_range = abs(x1 - x0); % x, y的范围
y_range = abs(y1 - y0);

sign_x = sign(x1 - x0); % 计算符号
sign_y = sign(y1 - y0);

steps = max(x_range, y_range); % 判断x，y的范围

step_x = x_range / steps; % 计算步长
step_y = y_range / steps;

if step_x == 1 % 确定步进方向
    x_res = x0 : step_x * sign_x : x1; % 结果集
    y_res = [];
    
    y = y0; % 给定初始值
    
    for x = x_res
        y_res = [y_res, round(y)]; % 保存四舍五入后的结果
        y = y + (step_y * sign_y); % 增长
    end
    
else
    x_res = []; % 结果集
    y_res = y0 : step_y * sign_y : y1;
    
    x = x0; % 给定初始值
    
    for y = y_res
        x_res = [x_res, round(x)]; % 保存四舍五入后的结果
        x = x + (step_x * sign_x); % 增长
    end
end
end

