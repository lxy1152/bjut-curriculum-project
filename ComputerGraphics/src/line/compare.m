% 比较两种方法的性能，返回两种方法的时间
function [t1, t2] = compare(times)
    x = randperm(times+1);
    y = randperm(times+1);
    tic
    for i = 1:times
        [~, ~] = dda(x(i), y(i), x(i+1), y(i+1));
    end
    t1 = toc;
    tic
    for i = 1:times
        [~, ~] = bresenham(x(i), y(i), x(i+1), y(i+1));
    end
    t2 = toc;
end

