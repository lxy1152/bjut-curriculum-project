function [x_res, y_res] = bre(x0, y0, x1, y1)
% 思路：
% 全部转为0<=k<=1的情况来处理，
% 所以其他情况可能需要交换x,y的位置或是对x,y取相反数
% 通过递归获得0<=k<=1情况下的点坐标，再交换或是取相反数即得原坐标点

% 经测试，效率不高，不如dda，此思路只做参考

x_range = x1 - x0; % 计算x,y的范围
y_range = y1 - y0;

x_flag = 0; % x,y交换标识符
y_flag = 0;

k = y_range / x_range; % 计算斜率

% 终止条件
if x_range > 0 && y_range > 0 && k <= 1 && k >= 0
    c1 = -2 * y_range; % 计算常量
    c2 = 2 * x_range + c1;
    
    p = x_range + c1; % 计算决策变量初始值
    
    y = y0; % y的初始值
    
    x_res = [x0]; % 结果集
    y_res = [y0];
    
    for x = x0 : x1-1 % 从第二个点开始循环
        x_res = [x_res, x + 1];
        if p <= 0
            y = y + 1;
            p = p + c2;
        else
            p = p + c1;
        end
        y_res = [y_res, y]; % 向结果集中加入数据
    end
    return
end

% 其他情况处理
if x_range < 0 % 如果x差值小于0，则取相反数
    x0 = -x0;
    x1 = -x1;
    x_flag = 1;
end

if y_range < 0 % 如果y差值小于0，则取相反数
    y0 = -y0;
    y1 = -y1;
    y_flag = 1;
end

if abs(k) > 1 % 如果k绝对值大于1， 则交换x，y计算
    [x, y] = bre(y0, x0, y1, x1);
    x_res = y;
    y_res = x;
else % 否则，即k∈（-1,1），不交换计算
    [x_res, y_res] = bre(x0, y0, x1, y1);
end

if x_flag % 如果x取过相反数，则对每一项取相反数
    for i = 1 : length(x_res)
        x_res(i) = -x_res(i); 
    end
end

if y_flag % 如果y取过相反数，则对每一项取相反数
    for i = 1 : length(y_res)
        y_res(i) = -y_res(i);
    end
end
end


