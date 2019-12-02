% 扫描线填充算法
function scan_line(x, y, color)
if length(x) ~= length(y) % 如数量不一致，返回
    disp("x与y的数量应保持一致！");
    return
end

x = [x, x(1)]; % 为了保证多边形闭合，将第一个点放入数组末尾
y = [y, y(1)];

y_min_all = min(y); % 求所有点中y的最小值与y的最大值
y_max_all = max(y);

y_base = y_min_all - 1; % 边表最小索引

y_size = y_max_all - y_min_all; % 求边表大小
etable = EdgeTable(y_size); % 实例化边表

for i=1:length(x)-1 % 建立边表
    x0 = x(i); % 当前点与下一个点
    y0 = y(i);
    x1 = x(i+1);
    y1 = y(i+1);
    plot([x0 x1], [y0 y1], "r");
    hold on;
    
    if y1 - y0 == 0 % 处理斜率为0的情况，即水平线，此时该直线不加入边表
        continue;
    elseif x1 - x0 == 0 % 处理斜率不存在的情况，即垂直线
        dx = 0;
        x_low = x0;
    else
        dx = (x1 - x0) / (y1 - y0); % 一般情况，求1/k
        if sign(dx) == -1 % 如果是下降趋势，则x最小值为x_min，否则为x_max
            x_low = max(x0, x1);
        else
            x_low = min(x0, x1);
        end
    end
    
    y_max = max(y0, y1) - 1; % 求出两点中y的最大值，因为要去除顶点，所以这里减一
    y_min = min(y0, y1); % 求出两点中y的最小值
    
    e = Edge(x_low, dx, y_max); % 实例化一条新的边，储存最低点的x值，x变化率，以及y的最大值
    etable.addEdge(y_min - y_base, e); % 在边表中加入这条边
end

aet = zeros(1,3); % 活动边表矩阵
aet_num = 1; % 活动边表矩阵中边的数量，默认为1
y_fill = y_min_all; % 设置填充位置，初始为最小y值处
for y_cyc_var = 1 : y_size 
    e = etable.getEdge(1, y_cyc_var); % 获得当前y值对应的线
    if ~isempty(e) % 如果有
        aet(aet_num,:) = [e.getXLow(), e.getDx(), e.getYMax()]; % 在矩阵中添加数据
        aet_num = aet_num + 1; % 总数量加一
    
        while ~isempty(e.getNext()) % 如果还有其他边，那么也添加到矩阵中去
            e = e.getNext();
            aet(aet_num,:) = [e.getXLow(), e.getDx(), e.getYMax()];
            aet_num = aet_num + 1;
        end
    end
    
    aet = sortrows(aet, 1);% 矩阵按照第一列排序
    for i = 1 : 2: length(aet)/2 + 1 % 两两填充，总长度除2，步长为2
        for p = ceil(aet(i, 1)) : floor(aet(i+1, 1))
            plot(p,  y_fill, color);
        end
        hold on;
        grid on;
    end

    f = find(aet(:,3) == y_fill); % 寻找当前达到y_max的直线
    if ~isempty(f) % 如果有
        aet(f,:) = []; % 将它们删除
        aet_num = aet_num - 1; % 总数量减一
    end

    aet(:,1) = aet(:,1) + aet(:,2); % x增长
    y_fill = y_fill + 1; % 填充位置增加
end
