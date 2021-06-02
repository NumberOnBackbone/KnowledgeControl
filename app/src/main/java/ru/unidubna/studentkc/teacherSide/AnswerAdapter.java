package ru.unidubna.studentkc.teacherSide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.unidubna.studentkc.R;
import ru.unidubna.studentkc.model.Answer;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<Answer> answers;

    AnswerAdapter(Context context, List<Answer> answers){
        this.inflater = LayoutInflater.from(context);
        this.answers = answers;
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.answer_create_recycle_item;
    }

    @Override
    public int getItemCount(){
        return answers.size();
    }


    @Override
    public void onBindViewHolder(AnswerAdapter.ViewHolder holder, int position){
        Answer answer = answers.get(position);
        if (answer.isRight()){
            holder.answerCorrect.setText("TRUE");
        } else {
            holder.answerCorrect.setText("FALSE");
        }
        holder.answerText.setText(answer.getAnswerText());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView answerText;
        final TextView answerCorrect;

        public ViewHolder(View itemView){
            super(itemView);
            answerText = itemView.findViewById(R.id.answer_text);
            answerCorrect = itemView.findViewById(R.id.answer_correct);
        }

        public TextView getAnswerText() {
            return answerText;
        }

        public TextView getAnswerCorrect() {
            return answerCorrect;
        }
    }
}
